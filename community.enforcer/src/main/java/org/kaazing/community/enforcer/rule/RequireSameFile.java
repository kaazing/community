package org.kaazing.community.enforcer.rule;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;

/**
 * Maven enforcer custom rule to check if two files are the same
 */
public class RequireSameFile implements EnforcerRule {

    private String originalFilePath;
    private String revisedFilePath;
    private String errorMessage;

    @Override
    public void execute(EnforcerRuleHelper helper) throws EnforcerRuleException {
        MavenProject project;
        try {
            project = (MavenProject) helper.evaluate("${project}");
            File originalFile = new File(project.getBasedir() + "\\" + originalFilePath);
            File revisedFile = new File(project.getBasedir() + "\\" + revisedFilePath);
            if (!FileUtils.contentEquals(originalFile, revisedFile)) {
                throw new EnforcerRuleException(errorMessage != null ? errorMessage : "The two compared files are not the same.");
            }
        } catch (ExpressionEvaluationException e) {
            throw new EnforcerRuleException("ExpressionEvaluationException thrown: ", e);
        } catch (IOException e) {
            throw new EnforcerRuleException("IOException thrown: ", e);
        }
    }

    /**
     * If your rule is cacheable, you must return a unique id when parameters or conditions change that would cause the
     * result to be different. Multiple cached results are stored based on their id. The easiest way to do this is to
     * return a hash computed from the values of your parameters. If your rule is not cacheable, then the result here is
     * not important, you may return anything.
     *
     * @return the cache id
     */
    @Override
    public String getCacheId() {
        return Integer.toString(originalFilePath.hashCode() + originalFilePath.hashCode());
    }

    /**
     * This tells the system if the results are cacheable at all. Keep in mind that during
     * forked builds and other things, a given rule may be executed more than once for the same
     * project. This means that even things that change from project to project may still 
     * be cacheable in certain instances.
     */
    @Override
    public boolean isCacheable() {
        return false;
    }

    /**
     * If the rule is cacheable and the same id is found in the cache, the stored results
     * are passed to this method to allow double checking of the results. Most of the time 
     * this can be done by generating unique ids, but sometimes the results of objects returned
     * by the helper need to be queried. You may for example, store certain objects in your rule
     * and then query them later.
     */
    @Override
    public boolean isResultValid(EnforcerRule arg0) {
        return false;
    }
}
