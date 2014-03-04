package itb.rpl.ppl.tgs2.znake.model.food;

/**
 *
 * @author edbert
 */
public class Effect {
    
    private int score;
    private String effectName; // added by Asri
    
    public Effect(int score, String effectName) { 
        this.score = score;
        this.effectName = effectName;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }
    
}
