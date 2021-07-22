package entity;

import event.commandEvent;

public abstract class Command {

    private final String name;

    public Command(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract void execute(commandEvent commandEvent);

}
