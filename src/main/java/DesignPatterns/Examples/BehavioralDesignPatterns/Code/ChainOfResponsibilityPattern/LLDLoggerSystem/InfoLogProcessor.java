package DesignPatterns.Examples.BehavioralDesignPatterns.Code.ChainOfResponsibilityPattern.LLDLoggerSystem;

public class InfoLogProcessor extends LogProcessor{

    InfoLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }
}
