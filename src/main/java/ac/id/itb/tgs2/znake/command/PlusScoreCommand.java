/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.command;

import ac.id.itb.tgs2.znake.utilities.ZnakeOperation;

/**
 *
 * @author wirasta1330
 */
public class PlusScoreCommand implements ZnakeCommand {
    
    private ZnakeOperation operation;
    private int score;
    
    public PlusScoreCommand(ZnakeOperation operation) {
        this(operation, 5);
    }
    
    public PlusScoreCommand(ZnakeOperation operation, int score) {
        this.operation = operation;
    }
    
    @Override
    public void execute() {
        //operation.plusScore(score);
    }
}
