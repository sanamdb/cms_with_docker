export function sumParamTwo(a: number, b: number): number {
    return a+b;
}

export function sumParamThree(a: number, b: number, c: number): number {
    return a+b+c;
}

export function mulParamTwo(a: number, b: number): number {
    return a*b;
}

export function mulParamThree(a: number, b: number, c: number): number {
    return a*b*c;
}

export function getName(): string[] {
    return ["Rahul", "Payal", "Sonam", "Sakshi"];
}

export function calculateEligibility(a: number): string {
    if(a<18)
        throw new Error('Minor');
    return "Eligible";
}