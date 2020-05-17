package com.github.hornta.racing.events;

import com.github.hornta.racing.objects.RacePlayerSession;
import com.github.hornta.racing.objects.RaceSession;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class RacePlayerGoalEvent extends Event {
  private static final HandlerList handlers = new HandlerList();
  private final RaceSession raceSession;
  private final RacePlayerSession playerSession;

  public RacePlayerGoalEvent(RaceSession raceSession, RacePlayerSession playerSession) {
    this.raceSession = raceSession;
    this.playerSession = playerSession;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  public RaceSession getRaceSession() {
    return raceSession;
  }

  public RacePlayerSession getPlayerSession() {
    return playerSession;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }
}
