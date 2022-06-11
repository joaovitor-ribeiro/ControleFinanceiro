import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GanhoFormComponent } from './ganho-form.component';

describe('GanhoFormComponent', () => {
  let component: GanhoFormComponent;
  let fixture: ComponentFixture<GanhoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GanhoFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GanhoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
