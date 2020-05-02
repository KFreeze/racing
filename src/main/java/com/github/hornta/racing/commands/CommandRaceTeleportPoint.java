package com.github.hornta.racing.commands;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.racing.RacingManager;
import com.github.hornta.racing.objects.RaceCheckpoint;
import io.papermc.lib.PaperLib;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandRaceTeleportPoint extends RacingCommand implements ICommandHandler {
  public CommandRaceTeleportPoint(RacingManager racingManager) {
    super(racingManager);
  }

  @Override
  public void handle(CommandSender commandSender, String[] args, int typedArgs) {
    RaceCheckpoint checkpoint = racingManager.getRace(args[0]).getCheckpoint(Integer.parseInt(args[1]));
    Player player = (Player)commandSender;
    PaperLib.teleportAsync(player, checkpoint.getLocation().add(0, -0.5, 0), PlayerTeleportEvent.TeleportCause.COMMAND);
  }
}
