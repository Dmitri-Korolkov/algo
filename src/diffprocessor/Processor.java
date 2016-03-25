package diffprocessor;

/**
 * Created by VavilauA on 6/19/2015.
 */
public class Processor {

    private long limit;

    public Processor(long limit) {
        // TODO: initialize.
        this.limit = limit;
    }

    public void doProcess(SortedLimitedList<Double> mustBeEqualTo, SortedLimitedList<Double> expectedOutput) {
        // TODO: make "mustBeEqualTo" list equal to "expectedOutput".
        // 0. Processor will be created once and then will be used billion times.
        // 1. Use methods: AddFirst, AddLast, AddBefore, AddAfter, Remove to modify list.
        // 2. Do not change expectedOutput list.
        // 3. At any time number of elements in list could not exceed the "Limit".
        // 4. "Limit" will be passed into Processor's constructor. All "mustBeEqualTo" and "expectedOutput" lists will have the same "Limit" value.
        // 5. At any time list elements must be in non-descending order.
        // 6. Implementation must perform minimal possible number of actions (AddFirst, AddLast, AddBefore, AddAfter, Remove).
        // 7. Implementation must be fast and do not allocate excess memory.

        SortedLimitedList.Entry expFirstItem = mustBeEqualTo.getFirst();
        SortedLimitedList.Entry mustBeFirstItem = expectedOutput.getFirst();
        if (mustBeEqualTo.getCount() > limit || expectedOutput.getCount() > limit) {
            return;
        }

        while (expFirstItem != null) {
            while (mustBeFirstItem != null && mustBeFirstItem.getValue().compareTo(expFirstItem.getValue()) < 0) {
                mustBeFirstItem = mustBeFirstItem.getNext();
            }
            if (mustBeFirstItem == null || mustBeFirstItem.getValue().equals(expFirstItem.getValue()) == false) {
                SortedLimitedList.Entry tmp = expFirstItem.getNext();
                mustBeEqualTo.remove(expFirstItem);
                expFirstItem = tmp;
            } else {
                expFirstItem = expFirstItem.getNext();
                mustBeFirstItem = mustBeFirstItem.getNext();
            }
        }

        expFirstItem = mustBeEqualTo.getFirst();
        mustBeFirstItem = expectedOutput.getFirst();
        while (mustBeFirstItem != null) {
            while (expFirstItem != null && expFirstItem.getValue().compareTo(mustBeFirstItem.getValue()) < 0) {
                expFirstItem = expFirstItem.getNext();
            }

            if (expFirstItem == null) {
                mustBeEqualTo.addLast((Double) mustBeFirstItem.getValue());
            } else if (expFirstItem.getValue().equals(mustBeFirstItem.getValue())) {
                expFirstItem = expFirstItem.getNext();
            } else {
                mustBeEqualTo.addBefore(expFirstItem, (Double) mustBeFirstItem.getValue());
            }
            mustBeFirstItem = mustBeFirstItem.getNext();
        }
    }
}
