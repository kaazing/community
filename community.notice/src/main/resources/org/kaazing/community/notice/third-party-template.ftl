<#--
  #%L
  License Maven Plugin
  %%
  Copyright (C) 2012 Codehaus, Tony Chemit
  %%
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Lesser Public License for more details.

  You should have received a copy of the GNU General Lesser Public
  License along with this program.  If not, see
  <http://www.gnu.org/licenses/lgpl-3.0.html>.
  #L%
  -->
<#-- To render the third-party file.
 Available context :

 - dependencyMap a collection of Map.Entry with
   key are dependencies (as a MavenProject) (from the maven project)
   values are licenses of each dependency (array of string)

 - licenseMap a collection of Map.Entry with
   key are licenses of each dependency (array of string)
   values are all dependencies using this license
-->
<#function artifactFormat p>
    <#if !p.groupId?contains("kaazing")>
        <#assign header = "This product depends on " + p.name + " " + p.version + "\n\n"/>
        <#assign mvnLicense = "\tLicense:\t"/>
        <#if !p.getLicenses()?has_content>
            <#-- Retrieve license from licenseMap -->
            <#list licenseMap as projList>
                <#list projList.getValue() as proj>
                    <#if proj == p>
                        <#assign mvnLicense = mvnLicense + projList.getKey() + "\n"/>
                    </#if>
                </#list>
            </#list>
        <#else>
            <#list p.getLicenses() as license>
                <#assign mvnLicense = mvnLicense + (license.url!"null") + " (" + (license.name!"null") + ")\n"/>
            </#list>
        </#if>
        <#assign homepage = ""/>
        <#if p.url?has_content>
            <#assign homepage = homepage + "\tHomepage:\t" + p.url + "\n"/>
        <#else>
            <#assign homepage = homepage + "\tThe project URL is not included in this dependency's pom, and thus could not be referenced here." + "\n"/>
        </#if>
        <#return header + mvnLicense + homepage>
    </#if>
</#function>
<#list dependencyMap as e>
    <#assign project = e.getKey()/>
    <#assign licenses = e.getValue()/>
    <#if artifactFormat(project)?has_content>
${artifactFormat(project)}
    </#if>
</#list>
