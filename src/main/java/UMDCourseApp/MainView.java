package UMDCourseApp;

import Objects.Section;
import Objects.Professor;
import Objects.Meeting;
import Services.ProfessorService;
import Services.SectionService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.util.*;

@Route("")
public class MainView extends VerticalLayout {


    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    Set<Professor> professorList;

    HorizontalLayout tabLayout = new HorizontalLayout();

    TextField searchField = new TextField();
    Tabs tabs = new Tabs();

    SectionService sectionServices;

    Label label1 = new Label();


    public MainView(){
        try{
            add(new H2("UMD's Highest Rated Professors"));
            searchCourse();
        }catch(Exception e){
            Notification notification = new Notification();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Course not found!");
        }
    }

    public void searchCourse(){

        searchField.getElement().setAttribute("aria-label", "search");
        searchField.setPlaceholder("Search for course");
        searchField.setClearButtonVisible(true);
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        searchField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        searchField.addKeyPressListener(Key.ENTER, event -> {
            String courseId = searchField.getValue();

            try {
                grabCourseSections(courseId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        add(searchField);
    }

    public void grabCourseSections(String courseId) throws IOException {

        label1.removeAll();
        sectionServices = new SectionService(restTemplateBuilder, courseId);

        add(label1);
        createProfTabs(sectionServices, courseId);
    }

    public void createProfTabs(SectionService sectionService, String courseId) throws IOException {

        Map<String, ArrayList<Section>> profSections = sectionService.getProfSections();

        tabs.removeAll();
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        Set<String> professors = sectionService.getCourseProfNames();
        professorList = new ProfessorService(restTemplateBuilder, professors).getProfessorsStats();
        String profName = null;

        TabSheet tabSheet = new TabSheet();
        tabSheet.setMaxWidth("1200px");

        for(Iterator<Professor> iter = professorList.iterator(); iter.hasNext(); ){
            Professor prof = iter.next();
            profName = prof.getName();
            List<Section> list = profSections.get(profName);
            if(list != null && list.size() > 0){
                Section[] sec = new Section[list.size()];
                sec = list.toArray(sec);
                Tab newTab = new Tab(new Span(prof.getName()), createBadgeRating(prof.getAverageRating()));
                tabSheet.add(newTab, new Span(sectionsAsTable(sec)));
            }
        }

        add(tabLayout);
        tabLayout.add(tabSheet);
    }

    public Component createBadgeRating(double rating) {
        double rate = (double) Math.round(rating * 100) / 100.0;
        Span badgeRating = new Span("\u2B50" + rate);
        badgeRating.getElement().getThemeList().add("badge small contrast");
        badgeRating.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badgeRating;
    }

    public Accordion sectionsAsTable(Section[] section){

        VerticalLayout verticalLayout = new VerticalLayout();
        Accordion accordion = new Accordion();
        List<VerticalLayout> test = new ArrayList<>();
        List<AccordionPanel> ap = new ArrayList<>();

        for(int i = 0; i < section.length; i++){
            test.add(detailContainer(section[i]));
            ap.add(accordion.add(section[i].getSectionId(), test.get(i)));
        }

        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);

        for(int i = 0; i < ap.size(); i++){
            ap.get(i).addThemeVariants(DetailsVariant.FILLED);
        }

        accordion.setMaxWidth("400px");

        return accordion;
    }

    public VerticalLayout detailContainer(Section section){
        VerticalLayout layoutLeft = new VerticalLayout();
        layoutLeft.getStyle().set("white-space","pre");

        if(section.getMeetings().size() > 0){
            Meeting lecture = section.getMeetings().get(0);

            if(lecture.startTime != null && lecture.endTime != null){
                layoutLeft.add(new H5("Lecture:        " + lecture.days + " " + lecture.startTime + " - " + lecture.endTime + " " + "@" + " " + lecture.building + lecture.room));
            }

            if(section.getMeetings().size() > 1){
                Meeting discussion = section.getMeetings().get(1);

                if(discussion.startTime != null && discussion.endTime != null){
                    layoutLeft.add(new H5("Discussion:  " + discussion.days + " " + discussion.startTime + " - " + discussion.endTime  + " " + "@" + " " + discussion.building + discussion.room));
                }
            }

            layoutLeft.add(new H5("Open seats:  " + section.getOpenSeats() + ", " + "Total seats: " + section.getSeats() + ", " + "Waitlist: " + section.getWaitlist()));
        }

        layoutLeft.setPadding(false);
        layoutLeft.setSpacing(false);

        return layoutLeft;
    }

}
