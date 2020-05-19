package edu;

import edu.Cards.CumulativeCard;
import edu.Cards.MultiJourneyCard;
import edu.Cards.TemporaryCard;
import edu.ControlSystem;
import edu.Pass;
import edu.Turnstile;
import edu.options.DurationType;
import edu.options.OwnerType;
import edu.options.TravelsCountType;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControlSystemTest {
    private ControlSystem controlSystem;
    @Before
    public void setUp() {
        addCardsAndTurnstiles();
    }

    @After
    public void tearDown() {
        ControlSystem.deleteInstance();
    }

    @Test
    public void testPasses(){
        for (Pass pass : controlSystem.passes) {
            System.out.println(pass.toString());
        }

    }

    public void addCardsAndTurnstiles() {
        controlSystem = ControlSystem.getInstance();

        controlSystem.createCard(7);
        controlSystem.createCard(44);
        controlSystem.createCard(OwnerType.PUPIL, TravelsCountType.FOUR);
        controlSystem.createCard(OwnerType.STUDENT, TravelsCountType.TEN);
        controlSystem.createCard(OwnerType.PUPIL, DurationType.MONTH);
        controlSystem.createCard(OwnerType.DEFAULT, DurationType.WEEK);
        controlSystem.createTurnstile();
        controlSystem.createTurnstile();
        controlSystem.createTurnstile();
        controlSystem.createTurnstile();

        controlSystem.turnstiles.get(0).acceptPass((CumulativeCard) controlSystem.getCardByIndex(0));
        controlSystem.turnstiles.get(0).acceptPass((CumulativeCard) controlSystem.getCardByIndex(0));
        controlSystem.turnstiles.get(1).acceptPass((CumulativeCard) controlSystem.getCardByIndex(1));
        controlSystem.turnstiles.get(2).acceptPass((CumulativeCard) controlSystem.getCardByIndex(1));
        controlSystem.turnstiles.get(3).acceptPass((MultiJourneyCard) controlSystem.getCardByIndex(2));
        controlSystem.turnstiles.get(0).acceptPass((TemporaryCard) controlSystem.getCardByIndex(4));
    }

    @Test
    public void testGetCountOfTries() {
        assertEquals(6,controlSystem.getCountOfTries());
    }

    @Test
    public void testGetCountOfPasses() {
        assertEquals(5, controlSystem.getCountOfPasses());
        assertNotEquals(6, controlSystem.getCountOfPasses());
        assertNotEquals("*", controlSystem.getCountOfPasses());
        assertNotEquals(0, controlSystem.getCountOfPasses());
    }

    @Test
    public void testGetCountOfFails() {
        assertEquals(1, controlSystem.getCountOfFails());
    }
    
    @Test
    public void testGetCountOfTriesByCumulativeCard() {
        assertEquals(4, controlSystem.getCountOfTriesByCumulativeCard());
        assertNotEquals(5, controlSystem.getCountOfTriesByCumulativeCard());
    }

    @Test
    public void testGetCountOfPassesByCumulativeCard() {
        assertEquals(3, controlSystem.getCountOfPassesByCumulativeCard());
        assertNotEquals(4, controlSystem.getCountOfPassesByCumulativeCard());
    }

    @Test
    public void testGetCountOfFailsByCumulativeCard() {
        assertEquals(1, controlSystem.getCountOfFailsByCumulativeCard());
        assertNotEquals(3, controlSystem.getCountOfFailsByCumulativeCard());
    }

    @Test
    public void testGetCountOfTriesByMultiJourneyCard() {
        assertEquals(1, controlSystem.getCountOfTriesByMultiJourneyCard("PUPIL", "FOUR"));
        assertNotEquals(3, controlSystem.getCountOfTriesByMultiJourneyCard("PUPIL", "FOUR"));
    }

    @Test
    public void testGetCountOfPassesByMultiJourneyCard() {
        assertEquals(1, controlSystem.getCountOfPassesByMultiJourneyCard("PUPIL", "FOUR"));
        assertNotEquals(3, controlSystem.getCountOfPassesByMultiJourneyCard("PUPIL", "FOUR"));
    }

    @Test
    public void testGetCountOfFailsByMultiJourneyCard() {
        assertEquals(0, controlSystem.getCountOfFailsByMultiJourneyCard("PUPIL", "FOUR"));
        assertNotEquals(3, controlSystem.getCountOfFailsByMultiJourneyCard("PUPIL", "FOUR"));
    }

    @Test
    public void testGetCountOfTriesByTemporaryCard() {
        assertEquals(0, controlSystem.getCountOfTriesByTemporaryCard("DEFAULT", "WEEK"));
        assertEquals(1, controlSystem.getCountOfTriesByTemporaryCard("PUPIL", "MONTH"));
    }
  
    @Test
    public void testGetCountOfPassesByTemporaryCard() {
        assertEquals(0, controlSystem.getCountOfPassesByTemporaryCard("DEFAULT", "WEEK"));
        assertEquals(1, controlSystem.getCountOfPassesByTemporaryCard("PUPIL", "MONTH"));
    }

    @Test
    public void testGetCountOfFailsByTemporaryCard() {
        assertEquals(0, controlSystem.getCountOfFailsByTemporaryCard("DEFAULT", "WEEK"));
        assertEquals(0, controlSystem.getCountOfFailsByTemporaryCard("PUPIL", "MONTH"));
    }
}