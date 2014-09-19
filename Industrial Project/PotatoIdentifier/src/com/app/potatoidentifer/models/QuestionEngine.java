package com.app.potatoidentifer.models;

import android.util.Pair;

import java.util.*;

/**
 * Created by Entitywan on 19/09/2014.
 */
public class QuestionEngine<Entity,Property> {


    protected HashMap<Entity, List<Property>> propertyMap;
    protected Map<Property, Boolean> knowledge = new HashMap<Property, Boolean>();


    public void QuestionEngine(List<Pair<Entity,Property>> rels)
    {
        propertyMap = new HashMap<Entity, List<Property>>();
        for(Pair<Entity, Property> P : rels)
        {
            if (!propertyMap.containsKey(P.first))
                propertyMap.put(P.first, new ArrayList<Property>());
            propertyMap.get(P.first).add(P.second);
        }

    }


    protected HashMap<Entity, List<Property>> getCandidates() {

        HashMap<Entity, List<Property>> candidates = new HashMap<Entity, List<Property>>(propertyMap);
        List<Entity> dead = new ArrayList<Entity>();

        for (Property C : knowledge.keySet()) {
            if (knowledge.get(C)) {
                for (Entity T : candidates.keySet()) {
                    if (!candidates.get(T).contains(C))
                        dead.add(T);
                }
            } else {
                for (Entity T : candidates.keySet()) {
                    if (candidates.get(T).contains(C))
                        dead.add(T);
                }
            }
        }


        for(Entity t : dead)
            candidates.remove(t);

        return candidates;
    }

    public List<Entity> getPossibleAnswers()
    {
        List<Entity> a = new ArrayList<Entity>();
        for (Entity A : getCandidates().keySet())
        {
            a.add(A);
        }
        return a;
    }


    public Property determineNextQuestion()
    {
        HashMap<Entity, List<Property>> candidates = getCandidates();

        if (candidates.size() < 2) // We know enough to narrow down the choice to 1 thing, or we have contradictory answers.
        {
            return null;
        }


        HashMap<Property, Integer> counts = new HashMap<Property, Integer>();
        for (Entity key : candidates.keySet()) {
            for (Property c : candidates.get(key))
            {
                counts.put(c, counts.get(c)+1);
            }

        }

        Property bestCandidate = null;
        int smallestDifference = Integer.MAX_VALUE;
        int targetValue = candidates.size()/2;

        for(Property v : counts.keySet())
        {
            if (Math.abs(counts.get(v) - targetValue) < smallestDifference && candidates.size() != counts.get(v))
            {
                bestCandidate = v;
            }
        }

        return bestCandidate;
    }
    
    public void Inform(Property P, boolean userAnswer) {
        knowledge.put(P, userAnswer);
    }




}
