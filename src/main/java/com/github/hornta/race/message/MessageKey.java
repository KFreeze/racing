package com.github.hornta.race.message;

import java.util.HashSet;
import java.util.Set;

public enum MessageKey {
  CREATE_RACE_SUCCESS("commands.create_race.success"),
  CREATE_RACE_NAME_OCCUPIED("commands.create_race.error_name_occupied"),
  DELETE_RACE_SUCCESS("commands.delete_race.success"),
  CHANGE_RACE_NAME_SUCCESS("commands.change_race_name.success"),
  ENABLE_RACE_SUCCESS("commands.enable_race.success"),
  ENABLE_RACE_IS_EDITING("commands.enable_race.error_is_editing"),
  ENABLE_RACE_IS_ENABLED("commands.enable_race.error_is_enabled"),
  DISABLE_RACE_SUCCESS("commands.disable_race.success"),
  DISABLE_RACE_IS_STARTED("commands.disable_race.error_is_started"),
  DISABLE_RACE_IS_DISABLED("commands.disable_race.error_is_disabled"),
  RACE_ADD_CHECKPOINT_SUCCESS("commands.race_add_checkpoint.success"),
  RACE_ADD_CHECKPOINT_IS_OCCUPIED("commands.race_add_checkpoint.error_is_occupied"),
  RACE_DELETE_CHECKPOINT_SUCCESS("commands.race_delete_checkpoint.success"),
  RACE_ADD_STARTPOINT_SUCCESS("commands.race_add_startpoint.success"),
  RACE_ADD_STARTPOINT_IS_OCCUPIED("commands.race_add_startpoint.error_is_occupied"),
  RACE_DELETE_STARTPOINT_SUCCESS("commands.race_delete_startpoint.success"),
  RACE_START_EDIT_SUCCESS("commands.race_start_edit.success"),
  RACE_START_EDIT_IS_EDITING("commands.race_start_edit.error_is_editing"),
  RACE_START_EDIT_IS_ENABLED("commands.race_start_edit.error_is_enabled"),
  RACE_STOP_EDIT_SUCCESS("commands.race_stop_edit.success"),
  RACE_STOP_EDIT_IS_NOT_EDITING("commands.race_stop_edit.error_is_not_editing"),
  RACE_SPAWN_IS_EDITING("commands.race_spawn.error_is_editing"),
  RACE_SET_SPAWN_SUCCESS("commands.race_set_spawn.success"),
  LIST_RACES_LIST("commands.list_races.race_list"),
  LIST_RACES_ITEM("commands.list_races.race_list_item"),
  RACE_SET_TYPE_SUCCESS("commands.race_set_type.success"),
  RACE_SET_TYPE_NOCHANGE("commands.race_set_type.error_nochange"),
  RACE_SET_SONG_SUCCESS("commands.race_set_song.success"),
  RACE_SET_SONG_NOCHANGE("commands.race_set_song.error_nochange"),
  RACE_UNSET_SONG_SUCCESS("commands.race_unset_song.success"),
  RACE_UNSET_SONG_ALREADY_UNSET("commands.race_unset_song.error_already_unset"),
  START_RACE_ALREADY_STARTED("commands.start_race.error_already_started"),
  START_RACE_MISSING_STARTPOINTS("commands.start_race.error_missing_startpoints"),
  START_RACE_MISSING_CHECKPOINT("commands.start_race.error_missing_checkpoints"),
  STOP_RACE_SUCCESS("commands.stop_race.success"),
  STOP_RACE_NOT_STARTED("commands.stop_race.error_not_started"),
  JOIN_RACE_SUCCESS("commands.join_race.success"),
  JOIN_RACE_NOT_OPEN("commands.join_race.error_not_open"),
  JOIN_RACE_IS_FULL("commands.join_race.error_is_full"),
  JOIN_RACE_IS_PARTICIPATING("commands.join_race.error_is_participating"),
  JOIN_RACE_IS_PARTICIPATING_OTHER("commands.join_race.error_is_participating_other"),
  RACE_SKIP_WAIT_NOT_STARTED("commands.race_skip_wait.error_not_started"),
  RELOAD_SUCCESS("commands.reload.success"),
  RELOAD_NOT_RACES("commands.reload.not_races"),

  RACE_NOT_FOUND("validators.race_not_found"),
  RACE_ALREADY_EXIST("validators.race_already_exist"),
  CHECKPOINT_NOT_FOUND("validators.checkpoint_not_found"),
  CHECKPOINT_ALREADY_EXIST("validators.checkpoint_already_exist"),
  STARTPOINT_NOT_FOUND("validators.startpoint_not_found"),
  STARTPOINT_ALREADY_EXIST("validators.startpoint_already_exist"),
  TYPE_NOT_FOUND("validators.type_not_found"),
  SONG_NOT_FOUND("validators.song_not_found"),

  RACE_IS_DISABLED("race_is_disabled"),
  RACE_CANCELED("race_canceled"),
  NOSHOW_DISQUALIFIED("race_start_noshow_disqualified"),
  QUIT_DISQULIAFIED("race_start_quit_disqualified"),
  EDIT_NO_EDIT_MODE("edit_no_edit_mode"),
  RACE_WIN("race_win"),
  PARTICIPATE_CLICK_TEXT("race_participate_click_text"),
  PARTICIPATE_HOVER_TEXT("race_participate_hover_text"),
  PARTICIPATE_TEXT("race_participate_text"),
  PARTICIPATE_TEXT_TIMELEFT("race_participate_text_timeleft"),
  RACE_COUNTDOWN("race_countdown_subtitle"),

  NO_PERMISSION_COMMAND("no_permission_command"),
  MISSING_ARGUMENTS_COMMAND("missing_arguments_command"),
  COMMAND_NOT_FOUND("command_not_found"),

  TIME_UNIT_SECOND("timeunit.second"),
  TIME_UNIT_SECONDS("timeunit.seconds"),
  TIME_UNIT_MINUTE("timeunit.minute"),
  TIME_UNIT_MINUTES("timeunit.minutes"),
  TIME_UNIT_HOUR("timeunit.hour"),
  TIME_UNIT_HOURS("timeunit.hours"),
  TIME_UNIT_DAY("timeunit.day"),
  TIME_UNIT_DAYS("timeunit.days");

  private static final Set<String> identifiers = new HashSet<>();
  static {
    for (MessageKey key : MessageKey.values()) {
      if(key.getIdentifier() == null || key.getIdentifier().isEmpty()) {
        throw new Error("A message identifier can't be null or empty.");
      }

      for(char character : key.name().toCharArray()) {
        if(Character.getType(character) == Character.LOWERCASE_LETTER) {
          throw new Error("All characters in a message key must be uppercase");
        }
      }

      for(char character : key.getIdentifier().toCharArray()) {
        if(Character.getType(character) == Character.UPPERCASE_LETTER) {
          throw new Error("All characters in a message identifier must be lowercase");
        }
      }

      if(identifiers.contains(key.getIdentifier())) {
        throw new Error("Duplicate identifier `" + key.getIdentifier() + "` found in MessageKey");
      }

      identifiers.add(key.getIdentifier());
    }
  }

  private String identifier;

  MessageKey(String identifier) {
    this.identifier = identifier;
  }

  public static boolean hasIdentifier(String identifier) {
    return identifiers.contains(identifier);
  }

  public String getIdentifier() {
    return identifier;
  }
}