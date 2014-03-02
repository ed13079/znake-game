/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.command;

import ac.id.itb.tgs2.znake.utilities.ZnakeOperation;

/**
 *
 * @author edbert
 */
public class MoveCommand implements ZnakeCommand {
    
    protected ZnakeOperation operation;
    
    public MoveCommand(ZnakeOperation operation) {
        this.operation = operation;
    }
    
    @Override
    public void execute() {
    }
    
}
