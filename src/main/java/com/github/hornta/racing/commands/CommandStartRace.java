package com.github.hornta.racing.commands;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.racing.RacingManager;
import org.bukkit.command.CommandSender;

public class CommandStartRace extends RacingCommand implements ICommandHandler {
  public CommandStartRace(RacingManager racingManager) {
    super(racingManager);
  }

  @Override
  public void handle(CommandSender commandSender, String[] args, int typedArgs) {
    int laps = args.length == 1 ? 1 : Integer.parseInt(args[1]);
    racingManager.tryStartRace(args[0], commandSender, laps);
  }
}
