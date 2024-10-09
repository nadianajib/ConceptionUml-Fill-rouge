import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PackServiceComponent } from './pack-service.component';

describe('PackServiceComponent', () => {
  let component: PackServiceComponent;
  let fixture: ComponentFixture<PackServiceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PackServiceComponent]
    });
    fixture = TestBed.createComponent(PackServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
