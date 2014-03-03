/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.util.command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class ZBroker {
   private List<ZnakeCommand> commandList = new ArrayList<ZnakeCommand>(); 

   public void addCommand(ZnakeCommand order){
      commandList.add(order);  
   }

   public void executeCommand(){
      for (ZnakeCommand command : commandList) {
         command.execute();
      }
      commandList.clear();
   }
}
