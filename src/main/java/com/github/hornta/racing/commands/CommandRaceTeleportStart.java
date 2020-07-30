package com.github.hornta.racing.commands;

import se.hornta.commando.ICommandHandler;
import com.github.hornta.racing.RacingManager;
import com.github.hornta.racing.Util;
import com.github.hornta.racing.objects.RaceStartPoint;
import io.papermc.lib.PaperLib;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandRaceTeleportStart extends RacingCommand implements ICommandHandler {
  public CommandRaceTeleportStart(RacingManager racingManager) {
    super(racingManager);
  }

  @Override
  public void handle(CommandSender commandSender, String[] args, int typedArgs) {
    RaceStartPoint checkpoint = racingManager.getRace(args[0]).getStartPoint(Integer.parseInt(args[1]));
    Player player = (Player)commandSender;

    Location loc = Util.snapAngles(checkpoint.getLocation());
    PaperLib.teleportAsync(player, loc, PlayerTeleportEvent.TeleportCause.COMMAND);
  }
}
