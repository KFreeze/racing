package com.github.hornta.racing.events;

import com.github.hornta.racing.objects.Race;
import org.bukkit.event.HandlerList;

public class RaceChangeStateEvent extends RaceEvent {
  private static final HandlerList handlers = new HandlerList();

  public RaceChangeStateEvent(Race race) {
    super(race);
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}