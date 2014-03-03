/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.util.command;

import itb.rpl.ppl.tgs2.znake.model.food.Food;
import itb.rpl.ppl.tgs2.znake.util.ZnakeOperation;

/**
 *
 * @author wirasta1330
 */
public class PlusScoreCommand implements ZnakeCommand {
    
    private ZnakeOperation operation;
    private int score;
    
//    public PlusScoreCommand(ZnakeOperation operation, Food food) {
//        this(operation, food.getEffect().getScore());
//    }
    
    public PlusScoreCommand(ZnakeOperation operation, Food food) {
        this.operation = operation;
        this.score = food.getEffect().getScore();
    }
    
    @Override
    public void execute() {
        operation.plusScore(score);
    }
}
