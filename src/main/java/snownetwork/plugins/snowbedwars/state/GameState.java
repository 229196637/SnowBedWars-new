package snownetwork.plugins.snowbedwars.state;

public enum GameState {
        Waiting,WaitStart,Gaming,BedBoom,Ending,Teleport;
        private static GameState bedWarsState;

        public static void setBedWarsState(GameState bedWarsState) {
            GameState.bedWarsState = bedWarsState;
        }

        public static boolean isBedWarsState(GameState bedWarsState2) {
            return bedWarsState==bedWarsState2;
        }

}
