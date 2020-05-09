package com.github.hornta.racing;

import com.github.hornta.racing.events.ConfigReloadedEvent;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SongManager implements Listener {
  private static SongManager instance;
  private final JavaPlugin plugin;
  private final Map<Player, RadioSongPlayer> playerRadios = new HashMap<>();
  private Map<String, Song> songsByName;
  private Set<String> songNames;

  private SongManager(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public static SongManager getInstance() {
    return instance;
  }

  public static void init(JavaPlugin plugin) {
    instance = new SongManager(plugin);
    instance.loadSongs((String) RacingPlugin.getInstance().getConfiguration().get(ConfigKey.SONGS_DIRECTORY));
  }

  public static Set<String> getSongNames() {
    return instance.songNames;
  }

  public static void playSong(String songName, Player player) {
    Song song = instance.songsByName.get(songName.toLowerCase(Locale.ENGLISH));
    if(song == null) {
      return;
    }

    stopSong(player);
    RadioSongPlayer radio = new RadioSongPlayer(song);
    radio.addPlayer(player);
    radio.setPlaying(true);
    instance.playerRadios.put(player, radio);
  }

  public static void stopSong(Player player) {
    if(instance.playerRadios.containsKey(player)) {
      RadioSongPlayer radio = instance.playerRadios.get(player);
      radio.setPlaying(false);
      radio.destroy();
      instance.playerRadios.remove(player);
    }
  }

  public static Song getSongByName(String name) {
    return instance.songsByName.get(name);
  }

  public void loadSongs(String pathToSongs) {
    loadSongs(new File(plugin.getDataFolder(), pathToSongs));
  }

  public void loadSongs(File directory) {
    directory.mkdirs();
    if(songsByName != null) {
      songsByName.clear();
      songNames.clear();
    } else {
      songsByName = new HashMap<>();
      songNames = new HashSet<>();
    }

    File[] files = directory.listFiles();

    if(files == null) {
      return;
    }

    for(File file : files) {
      if(file.isDirectory()) {
        loadSongs(file);
        continue;
      }

      if(file.isFile()) {
        String name = Util.getFilenameWithoutExtension(file)
          .toLowerCase(Locale.ENGLISH)
          .replaceAll(" ", "_")
          .replaceAll("[^A-Za-z0-9_]", "");
        Song song = NBSDecoder.parse(file);
        songsByName.put(name, song);
        songNames.add(name);
      }
    }
  }

  @EventHandler
  void onConfigReloaded(ConfigReloadedEvent event) {
    loadSongs((String) RacingPlugin.getInstance().getConfiguration().get(ConfigKey.SONGS_DIRECTORY));
  }
}
