import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResgistrercomponentComponent } from './resgistercomponent.component';

describe('ResgistrercomponentComponent', () => {
  let component: ResgistrercomponentComponent;
  let fixture: ComponentFixture<ResgistrercomponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResgistrercomponentComponent]
    });
    fixture = TestBed.createComponent(ResgistrercomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
