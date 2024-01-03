package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import world.Level;

public class MouseHandler implements MouseListener {
    public GamePanel gp;



    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gp.state == State.MENU){
        int PlayX = (int)((gp.tileSize * gp.scale * Level.getInstance().getArena()[0].length)*0.7);
        int PlayY = (int)((gp.tileSize*gp.scale*Level.getInstance().getArena().length)*0.7);
        int mx = e.getX();
        int my = e.getY();

        if (mx> PlayX && mx <= PlayX+ (int)(352*0.4)*gp.scale){
            if (my >PlayY && my <= PlayY+(int)(165*0.4)*gp.scale){
                gp.setState(State.GAME);
            }
        }
        }
        // throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
    
}
