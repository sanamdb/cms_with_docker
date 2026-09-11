import { FontAdminDirective } from './font-admin.directive';

describe('FontAdminDirective', () => {
  it('should create an instance', () => {
    // Create a fake ElementRef
    const mockElementRef = { nativeElement: document.createElement('div') };
    const mockRenderer = {} as any; // Mock other dependency if needed

    const directive = new FontAdminDirective(mockElementRef, mockRenderer);
    expect(directive).toBeTruthy();
  });
});
