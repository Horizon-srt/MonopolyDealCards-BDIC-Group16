package Modules.Piles.impl;

import Core.ITerminalView;
import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Cards.impl.Card;
import Modules.Piles.iface.IPropertyPile;
import utils.Color;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import static utils.Type.PROPERTY;

public class PropertyPile implements IPropertyPile {
    private LinkedList<LinkedList<IPropertyCard>> propertyList;
    public PropertyPile() { propertyList = new LinkedList<>(); }
    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card c) {
        int index = TerminalView.getTerminalView().getIndex();
        if (index <= propertyList.size()) {
            LinkedList<IPropertyCard> list = propertyList.get(index-1);
            //TODO 缺乏判断c颜色是否合规
            list.add((IPropertyCard) c);
        } else {
            LinkedList<IPropertyCard> list = new LinkedList<>();
            list.add((IPropertyCard) c);
            propertyList.add(list);
        }
    }

    @Override
    public void arrangement() {
    }

    @Override
    public int size() {
        return propertyList.size();
    }

    @Override
    public void listCards() {
        Iterator<LinkedList<IPropertyCard>> it = propertyList.iterator();
        int index = 1;
        while (it.hasNext()){
            LinkedList<IPropertyCard> list = it.next();
            int indexx = 1;
            for (IPropertyCard card : list) {
                System.out.print("Properties"+index+": ");
                System.out.print("Index: "+indexx+", ");
                System.out.print("Name: "+card.getName()+",  ");
                System.out.print("Value: "+card.getValue()+";  ");
                System.out.print("Color: "+card.getColor()+";  ");
                System.out.println(" ");
                indexx++;
            }
            index++;
        }
    }

    @Override
    public Card getCardById(int id) {
        for (LinkedList<IPropertyCard> list : propertyList){
            if (id > list.size()){
                id = id - list.size();
            } else {
                IPropertyCard card = list.get(id-1);
                list.remove(id -1);
                return (Card) card;
            }
        }
        return null;
    }

    @Override
    public boolean isWin() {
        return false;
    }

    Map<String, Integer> map = Map.of(
            "BROWN",2,
            "BLUE",2,
            "GREEN",3,
            "LIGHT_BLUE",3,
            "ORANGE",3,
            "PINK",3,
            "RAILROAD",4,
            "RED",3,
            "UTILITY",2,
            "YELLOW",3);
            
    public boolean isFull(LinkedList<IPropertyCard> list, String color) {
        for (IPropertyCard card: list){
            String c = card.getColor().toString();
            if (c != "ALL" && c != color){
                return false;
            }
        }
        if (list.size() == map.get(color)){
            return true;
        }
        return false;
    }

    public void removeCard(Card c){
        Iterator<LinkedList<IPropertyCard>> it = propertyList.iterator();
        while (it.hasNext()){
            LinkedList list = it.next();
            Iterator<Card> itt = list.iterator();
            while (itt.hasNext()){
                Card card = itt.next();
                if (card.name == c.name){
                    list.remove(c);
                }
            }
        }
    }
}
