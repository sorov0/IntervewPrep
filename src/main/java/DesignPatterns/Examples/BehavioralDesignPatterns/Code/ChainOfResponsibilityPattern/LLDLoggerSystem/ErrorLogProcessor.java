package DesignPatterns.Examples.BehavioralDesignPatterns.Code.ChainOfResponsibilityPattern.LLDLoggerSystem;

public class ErrorLogProcessor extends LogProcessor{

    ErrorLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }
}
