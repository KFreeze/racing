package com.github.hornta.racing.commands.argumentHandlers;

import se.hornta.commando.ValidationResult;
import se.hornta.commando.completers.IArgumentHandler;
import com.github.hornta.racing.enums.RaceStatType;
import com.github.hornta.racing.MessageKey;
import se.hornta.messenger.MessageManager;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class RaceStatArgumentHandler implements IArgumentHandler {

  @Override
  public Set<String> getItems(CommandSender sender, String argument, String[] prevArgs) {
    return Arrays.stream(RaceStatType.values())
      .map(RaceStatType::name)
      .map((String s) -> s.toLowerCase(Locale.ENGLISH))
      .filter(type -> type.startsWith(argument.toLowerCase(Locale.ENGLISH)))
      .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Override
  public boolean test(Set<String> items, String argument) {
    return items.contains(argument.toLowerCase(Locale.ENGLISH));
  }

  @Override
  public void whenInvalid(ValidationResult result) {
    MessageManager.setValue("stat_type", result.getValue());
    MessageManager.setValue("stat_types", Arrays.stream(RaceStatType.values()).map(RaceStatType::name).collect(Collectors.joining(", ")));
    MessageManager.sendMessage(result.getCommandSender(), MessageKey.STAT_TYPE_NOT_FOUND);
  }
}
