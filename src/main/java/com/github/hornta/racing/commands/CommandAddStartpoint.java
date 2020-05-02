package com.github.hornta.racing.commands;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.racing.RacingManager;
import com.github.hornta.racing.Util;
import com.github.hornta.racing.enums.RaceState;
import com.github.hornta.racing.MessageKey;
import com.github.hornta.messenger.MessageManager;
import com.github.hornta.racing.objects.Race;
import com.github.hornta.racing.objects.RaceStartPoint;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddStartpoint extends RacingCommand implements ICommandHandler {
  public CommandAddStartpoint(RacingManager racingManager) {
    super(racingManager);
  }

  @Override
  public void handle(CommandSender commandSender, String[] args, int typedArgs) {
    Race race = racingManager.getRace(args[0]);
    
    if(race.getState() != RaceState.UNDER_CONSTRUCTION) {
      MessageManager.setValue("race_name", race.getName());
      MessageManager.sendMessage(commandSender, MessageKey.EDIT_NO_EDIT_MODE);
      return;
    }

    Player player = (Player)commandSender;
    if(race.getStartPoint(player.getLocation()) != null) {
      MessageManager.sendMessage(commandSender, MessageKey.RACE_ADD_STARTPOINT_IS_OCCUPIED);
      return;
    }

    racingManager.addStartPoint(Util.centerOnBlockHorizontally(player.getLocation()), race, (RaceStartPoint startPoint) -> {
      MessageManager.setValue("race_name", race.getName());
      MessageManager.setValue("position", startPoint.getPosition());
      MessageManager.sendMessage(player, MessageKey.RACE_ADD_STARTPOINT_SUCCESS);
    });
  }
}
