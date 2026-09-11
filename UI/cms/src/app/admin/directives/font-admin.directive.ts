import { Directive, ElementRef, HostListener, Input, OnInit, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appFontAdmin]'
})
export class FontAdminDirective implements OnInit {

  @Input() appFontAdmin: string = '';

  constructor(
    private elementRef: ElementRef,
    private rendrer2: Renderer2
  ) { }

  ngOnInit(): void {
    this.elementRef.nativeElement.style.fontFamily="Arial, sans-serif";
    this.rendrer2.setStyle(this.elementRef.nativeElement,"fontWeight", "bold");
    //this.elementRef.nativeElement.style.fontWeight = this.appFontAdmin;
  }

  @HostListener("mouseenter")
  mouseEnterEvent() {
    this.elementRef.nativeElement.style.fontWeight = "normal";
  }

  @HostListener("mouseleave")
  mouseLeaveEvent() {
    this.elementRef.nativeElement.style.fontWeight = "bold";
  }

}
