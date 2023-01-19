package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Sujet {
    private List<IObserveur> observerList = new ArrayList<>();

    public void attacher(IObserveur o){
        observerList.add(o);
    }
    public void detacher(IObserveur o){
        observerList.remove(o);
    }

    public void notifier(){
        for (IObserveur o : observerList) {
            o.update();
        }
    }
}
