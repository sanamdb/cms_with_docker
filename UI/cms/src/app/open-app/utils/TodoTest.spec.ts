import { calculateEligibility, sumParamThree, sumParamTwo } from "./CommonUtils";

describe('Sum Methods Test', () => {

    beforeEach(() => {
        console.log("before each");
    })

    afterEach(() => {
        console.log("After each");
    })

    it('Sum with 2 param test', () => {
        const actual = sumParamTwo(5, 7);
        expect(actual).toBe(12);
    });

    it.skip('Sum with 3 param test', () => {
        const actual = sumParamThree(5, 7, 3);
        expect(actual).toBe(15);
    });

    it.todo('Upcoming method test');

    it.each([
        {age: 23, expected: 'Eligible'},
        {age: 27, expected: 'Eligible'},
        {age: 18, expected: 'Eligible'},
        {age: 13, expected: new Error('Minor')}
    ])
    ('Age Eligibility test with $age', ({ age, expected }) => {

        if(expected instanceof Error) {
            expect(() => calculateEligibility(age)).toThrow('Minor');
        } else {
            expect(calculateEligibility(age)).toBe(expected);
        }
    })
});
