package com.my.base.jar.debug.jdi;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Location;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.StringReference;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.Value;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.VMDisconnectEvent;
import com.sun.jdi.event.VMStartEvent;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.ClassPrepareRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;

import java.util.List;
import java.util.Map;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/7/31
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class JdiDebugger {
    static VirtualMachine vm;
    static Process process;
    static EventRequestManager eventRequestManager;
    static EventQueue eventQueue;
    static EventSet eventSet;
    static boolean vmExit = false;

    public static void main(String[] args) throws Exception {
        //获取虚拟机管理器（VirtualMachineManager）
        //获取默认的链接器（Connector）
        LaunchingConnector launchingConnector
                = Bootstrap.virtualMachineManager().defaultConnector();

        // Get arguments of the launching connector
        Map<String, Connector.Argument> defaultArguments
                = launchingConnector.defaultArguments();

        Connector.Argument mainArg = defaultArguments.get("main");
        Connector.Argument suspendArg = defaultArguments.get("suspend");
        // Set class of main method
        mainArg.setValue("com.my.base.jar.debug.jdi.JdiHelloWorld");
        suspendArg.setValue("true");
        vm = launchingConnector.launch(defaultArguments);

        process = vm.process();

        // Register ClassPrepareRequest
        eventRequestManager = vm.eventRequestManager();
        ClassPrepareRequest classPrepareRequest
                = eventRequestManager.createClassPrepareRequest();
        classPrepareRequest.addClassFilter("com.my.base.jar.debug.jdi.JdiHelloWorld");
        classPrepareRequest.addCountFilter(1);
        classPrepareRequest.setSuspendPolicy(EventRequest.SUSPEND_ALL);
        classPrepareRequest.enable();

        // Enter event loop
        eventLoop();

        process.destroy();
    }

    private static void eventLoop() throws Exception {
        eventQueue = vm.eventQueue();
        while (true) {
            if (vmExit == true) {
                break;
            }
            eventSet = eventQueue.remove();
            EventIterator eventIterator = eventSet.eventIterator();
            while (eventIterator.hasNext()) {
                Event event = (Event) eventIterator.next();
                execute(event);
            }
        }
    }

    private static void execute(Event event) throws Exception {
        if (event instanceof VMStartEvent) {
            System.out.println("VM started");
            eventSet.resume();
        } else if (event instanceof ClassPrepareEvent) {
            ClassPrepareEvent classPrepareEvent = (ClassPrepareEvent) event;
            String mainClassName = classPrepareEvent.referenceType().name();
            if (mainClassName.equals("com.my.base.jar.debug.jdi.JdiHelloWorld")) {
                System.out.println("Class " + mainClassName
                        + " is already prepared");
            }
            if (true) {
                // Get location
                ReferenceType referenceType = classPrepareEvent.referenceType();
                List locations = referenceType.locationsOfLine(10);
                Location location = (Location) locations.get(0);

                // Create BreakpointEvent
                BreakpointRequest breakpointRequest = eventRequestManager
                        .createBreakpointRequest(location);
                breakpointRequest.setSuspendPolicy(EventRequest.SUSPEND_ALL);
                breakpointRequest.enable();
            }
            eventSet.resume();
        } else if (event instanceof BreakpointEvent) {
            System.out.println("Reach line 10 of com.ibm.jdi.test.HelloWorld");
            BreakpointEvent breakpointEvent = (BreakpointEvent) event;
            ThreadReference threadReference = breakpointEvent.thread();
            StackFrame stackFrame = threadReference.frame(0);
            LocalVariable localVariable = stackFrame
                    .visibleVariableByName("str");
            Value value = stackFrame.getValue(localVariable);
            String str = ((StringReference) value).value();
            System.out.println("The local variable str at line 10 is " + str
                    + " of " + value.type().name());
            eventSet.resume();
        } else if (event instanceof VMDisconnectEvent) {
            vmExit = true;
        } else {
            eventSet.resume();
        }
    }
}
