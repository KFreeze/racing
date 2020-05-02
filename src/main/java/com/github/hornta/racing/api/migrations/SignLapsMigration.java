package com.github.hornta.racing.api.migrations;

import com.github.hornta.racing.api.IFileMigration;
import com.github.hornta.racing.enums.RaceVersion;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;
import java.util.Map;

public class SignLapsMigration implements IFileMigration {
  @Override
  public RaceVersion from() {
    return RaceVersion.V10;
  }

  @Override
  public RaceVersion to() {
    return RaceVersion.V11;
  }

  @Override
  public void migrate(YamlConfiguration yamlConfiguration) {
    @SuppressWarnings("unchecked")
    List<Map<String, Object>> entries = (List<Map<String, Object>>)yamlConfiguration.getList("signs");
    for(Map<String, Object> sign : entries) {
      sign.put("laps", 1);
    }
    yamlConfiguration.set("signs", entries);
  }
}
