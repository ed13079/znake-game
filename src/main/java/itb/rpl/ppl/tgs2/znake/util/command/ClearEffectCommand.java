/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.util.command;

import itb.rpl.ppl.tgs2.znake.util.ZnakeOperation;

/**
 *
 * @author edbert
 */
public class ClearEffectCommand implements ZnakeCommand {
    
    private ZnakeOperation operation;
    
    public ClearEffectCommand(ZnakeOperation operation) {
        this.operation = operation;
    }
    
    public void execute() {
        operation.clearEffect();
    }
}
