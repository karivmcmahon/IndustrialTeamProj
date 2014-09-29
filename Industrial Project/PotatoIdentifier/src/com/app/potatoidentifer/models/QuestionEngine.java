package com.app.potatoidentifer.models;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ewan on 19/09/2014.
 */
public class QuestionEngine<Entity, Property> {


    protected HashMap<Entity, List<Property>> propertyMap;
    protected Map<Property, Boolean> knowledge = new HashMap<Property, Boolean>();

    public QuestionEngine(List<Pair<Entity,Property>> rels)
    {



        propertyMap = new HashMap<Entity, List<Property>>();
        for (Pair<Entity, Property> P : rels) {
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


        for (Entity t : dead)
            candidates.remove(t);

        return candidates;
    }

    public List<Entity> getPossibleAnswers() {
        List<Entity> a = new ArrayList<Entity>();
        for (Entity A : getCandidates().keySet()) {
            a.add(A);
        }
        return a;
    }


    public Property determineNextQuestion() {
        HashMap<Entity, List<Property>> candidates = getCandidates();

        if (candidates.size() < 2) // We know enough to narrow down the choice to 1 thing, or we have contradictory answers.
        {
            return null;
        }


        HashMap<Property, Integer> counts = new HashMap<Property, Integer>();
        for (Entity key : candidates.keySet()) {
            for (Property c : candidates.get(key)) {
                if (!counts.containsKey(c))
                    counts.put(c,0);
                counts.put(c, counts.get(c) + 1);
            }

        }

        Property bestCandidate = null;
        int smallestDifference = Integer.MAX_VALUE;
        int targetValue = candidates.size() / 2;

        for (Property v : counts.keySet()) {

            Log.d("dict", v.toString() + " -> " + counts.get(v).toString() + " -> "+ ((Integer)Math.abs(counts.get(v) - targetValue)).toString());

            if (Math.abs(counts.get(v) - targetValue) < smallestDifference && candidates.size() != counts.get(v)) {
                bestCandidate = v;
                smallestDifference =Math.abs(counts.get(v) - targetValue);

            }
        }

        return bestCandidate;
    }

    public void Inform(Property P, boolean userAnswer) {
        knowledge.put(P, userAnswer);
    }

    public void Forget(Property P) { knowledge.remove(P); }
    public void ForgetAll() { knowledge.clear(); }

    public static String[] symptoms = new String[]
            {
                    "Pectobacterium atrosepticum", "yellow colouring",
                    "Pectobacterium atrosepticum", "slimy base",
                    "Pectobacterium atrosepticum", "wilting",
                    "Dickeya solani", "brown colouring in stem",
                    "Dickeya solani", "wilting",
                    "Brown rot", "yellow colouring",
                    "Brown rot", "wilting",
                    "Brown rot", "brown streaks",
                    "Brown rot", "ooze in cut stems",
                    "Early blight","small black spots",
                    "Early blight", "circular brown patches",
                    "Early blight", "leaf curling",
                    "Late blight", "pale green spots",
                    "Late blight", "irregular black/brown patches",
                    "Late blight", "white mycelium",
                    "Potassium deficiency", "fewer stems",
                    "Potassium deficiency", "leaf curling",
                    "Potassium deficiency", "necrotic leaf edges",
                    "Nitrogen deficiency", "leaf curling",
                    "Nitrogen deficiency", "stunting",
                    "Nitrogen deficiency", "yellow leaves",
                    "Phosphorus deficiency", "stunting",
                    "Phosphorus deficiency", "leaf crinkling",
                    "Phosphorus deficiency", "necrotic leaf edges",
                    "Phosphorus deficiency", "chlorisis",
                    "Potato virus Y", "yellow colouring",
                    "Potato virus Y", "mosaic leaves",
                    "Potato virus Y", "leaf curling",
                    "Potato virus X", "mosaic leaves",
                    "Potato virus X", "green colouring",
                    "Potato virus X", "blotchy leaves",
                    "Leafroll virus", "leaf curling",
                    "Leafroll virus", "crisp/hard leaves",
                    "Common scab", "raised/pitted brown tuber lesions",
                    "Black scurf", "stunting",
                    "Black scurf", "stem lesions",
                    "Black scurf", "black patches on tubers",
                    "Black scurf", "misshapen tubers",
                    "Fusarium dry rot", "wilting",
                    "Fusarium dry rot", "stunting",
                    "Fusarium dry rot", "large black/brown patches",
                    "Fusarium dry rot", "white mycelium",
                    "Root knot nematode", "wilting",
                    "Root knot nematode", "large root knots",
                    "Root knot nematode", "chlorea",
                    "Root knot nematode", "root damage",
                    "Late blight", "Pale green spots",
                    "Late blight", "white mercelium",
                    "Late blight", "irregular black/brown patches",
                    "Aphids", "feeding damage",
                    "Aphids", "wilting",
                    "Tuber moth", "holes/tunnels in tubers"
            };






}