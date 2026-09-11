import { calculateEligibility, getName, mulParamThree, mulParamTwo, sumParamThree, sumParamTwo } from "./CommonUtils";


describe('Common Utils Test Suites', () => {

    describe('Sum Methods Test', () => {

        beforeEach(()=> {
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

    });

    describe.skip('Multiplication Methods Test', () => {

        it('Mul with 2 param test', () => {
            const actual = mulParamTwo(4, 5);
            expect(actual).toBe(20);
        });

        it('Mul with 3 param test', () => {
            const actual = mulParamThree(4, 5, 3);
            expect(actual).toBe(60);
        });

    });

    describe('Matchers Explore Test Suite', () => {

        it.only('Sum Matchers Test', () => {
            const actual = sumParamTwo(3, 5);
            expect(actual).toBe(8);
            expect(actual).toEqual(8);
            expect(actual).not.toBe(6);
            expect(actual).not.toBeNull();
            expect(actual).toBeDefined();
            expect(actual).not.toBeUndefined();
            expect(actual).toBeTruthy();
        });

        it('Matcher2', () => {
            const actual = sumParamTwo(7, 3);
            expect(actual).not.toBeFalsy();
        })
        
        it('Matcher 3', () => {
            const actual = sumParamTwo(7, 3);
            expect(actual).toBeGreaterThan(9);
            expect(actual).toBeGreaterThanOrEqual(10);
            expect(actual).toBeLessThan(11);
            expect(actual).toBeLessThanOrEqual(10);
        });

        it('Array test', () => {
            const actual = getName();
            expect(actual).toContain("Sonam");
        });


        it('eligibility test positive', () => {
            const actual = calculateEligibility(28);
            expect(actual).toMatch('Eligible');
        });

        it('eligibility test exception', () => {
            expect(() => calculateEligibility(4)).toThrow('Minor');
        });

    });

});