public enum Direction {

    UP('U'){
        @Override
        public int [] updateCoordinates(CircleMarker circleMarker, int spaces){
            int x = circleMarker.getX();
            int y = circleMarker.getY() - spaces;
            return new int [] {x, y};
        }
    },
    DOWN('D'){
        @Override
        public int [] updateCoordinates(CircleMarker circleMarker, int spaces) {
            int x = circleMarker.getX();
            int y = circleMarker.getY() + spaces;
            return new int[]{x, y};
        }
    },
    LEFT('L'){
        @Override
        public int [] updateCoordinates(CircleMarker circleMarker, int spaces) {
            int x = circleMarker.getX() -spaces;
            int y = circleMarker.getY() ;
            return new int[]{x, y};
        }
    },
    RIGHT('R'){
        @Override
        public int [] updateCoordinates(CircleMarker circleMarker, int spaces) {
            int x = circleMarker.getX() +  spaces;
            int y = circleMarker.getY();
            return new int[]{x, y};
        }
    };

    private final char label;

    Direction(char label) {
        this.label = label;
    }

    public abstract int [] updateCoordinates(CircleMarker circleMarker, int spaces);
    

    public static int [] getNewCoordinatesForDirection(char direction, CircleMarker marker, int spaces){

        for (Direction d: Direction.values()) {
               if(d.label == direction) {
                   return d.updateCoordinates(marker, spaces);
               }
            }
        throw new UnsupportedOperationException("Unsupported operation exception");
    }
}
