package io.william.jbehave.demo.basic;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import steps.TraderSteps;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringJBehaveDemo.class)
public class SpringJBehaveDemo extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                        //note:it's important to avoid report error.
                        .withRelativeDirectory("jbehave-report")
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML));
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(
                codeLocationFromClass(this.getClass()), "stories/*.story", null);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(
                configuration(), new TraderSteps());
    }

}
