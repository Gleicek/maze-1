/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.util.ArrayList;
import Classes.Board;
import Classes.Node;

/**
 *
 * @author EngComp
 */
public abstract class Search {
    
    public class NoSuchPathException extends Exception{}
    
    private Node  _begin;
    private Node  _target;
    private Node  _current;
    
    private Board _board;
    
    public Search(Board board) {
        this._board     = board;
        this._begin     = board.getBegin();
        this._target    = board.getEnd();   
    }

    public Node getBegin() {
        return _begin;
    }

    public Node getTarget() {
        return _target;
    }

    public void setBegin(Node _begin) {
        this._begin = _begin;
    }

    public void setTarget(Node _target) {
        this._target = _target;
    }
    
    public abstract boolean isEmpty();
    public abstract Node remove();
    public abstract void add(Node current, Node parent);
    
    @SuppressWarnings("empty-statement")
    public Node run() throws NoSuchPathException{ 
        add(_begin, null);
        while(!isEmpty() && next());
        
        if(_current == null || !_current.equals(_target))
            throw new NoSuchPathException();
        
        return _current;
    }
    
    public boolean next() {
        _current = remove();
        if(_current.equals(_target))
            return false;
        ArrayList<Node> nodes = _current.getAdjList();
        for(Node node : nodes)
            add(node, _current);
        return true;
    }
}