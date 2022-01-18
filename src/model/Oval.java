package model;


import sound.MidiSynth;

import java.awt.*;


public class Oval extends Shape {


    public Oval(Point topLeft, MidiSynth midiSynth) {
        this((int) topLeft.getX(), (int) topLeft.getY(), 0, 0); //note to students: calls the other constructor!
        selected = false;
        this.midiSynth = midiSynth;
        instrument = 0;
        playLineCoord = 0;
        PLAYING_COLOR = new Color(230, 158, 60);
    }


    public Oval(int x, int y, int w, int h) {
        super(x, y, w, h);
    }


}
