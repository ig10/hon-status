package devo.honstatus;

import java.util.Map;

public class HonURL {

  private String base, request;
  private String stats, game, player;

  private static final String HON_API = 'http://api.heroesofnewerth.com';
  private static final HashMap<String, String> statsList = new HashMap() {{
    put("player", "player_statistics");
    put("hero", "hero_statistics");
    put("match", "match_history");
  }};


  HonURL( String stats, String game, String player ) {
    this.base = HON_API;
    this.request = this.base;

    this.stats = stats;
    this.game = game;
    this.player = player;

    build();
  }

  // Getters

  public String baseUrl() {
    return this.base;
  }

  public String requestUrl() {
    return this.request;
  }

  // Custom Methods

  private void build() {

  }

  private void buildStats() {
    // player_statistics
    // hero_statistics
    // match_history


    this.request += "/" + statsList.get(this.player);
  }

  private void buildGame() {
    // ranked
    // casual
    // public
    // all
  }

  private void buildPlayer() {
    // nickname/player
  }

}
