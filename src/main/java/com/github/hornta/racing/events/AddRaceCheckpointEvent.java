package com.github.hornta.racing.events;

import com.github.hornta.racing.objects.Race;
import com.github.hornta.racing.objects.RaceCheckpoint;
import org.bukkit.event.HandlerList;

public class AddRaceCheckpointEvent extends RaceCheckpointEvent {
  private static final HandlerList handlers = new HandlerList();

  public AddRaceCheckpointEvent(Race race, RaceCheckpoint checkpoint) {
    super(race, checkpoint);
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
