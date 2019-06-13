<?xml version="1.0"?>
<recipe>
    <!-- Fragment -->
    <instantiate from="root/src/app_package/MvpFragment.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className}Fragment.kt" />

    <!-- Model -->
    <instantiate from="root/src/app_package/MvpModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentModel.kt" />

    <!-- View -->
    <instantiate from="root/src/app_package/MvpView.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentView.kt" />

    <!-- Interactor -->
    <instantiate from="root/src/app_package/MvpInteractor.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentInteractor.kt" />

    <!-- Presenter -->
    <instantiate from="root/src/app_package/MvpPresenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentPresenter.kt" />

    <!-- Layout -->
    <instantiate from="root/src/app_package/MvpLayout.xml.ftl"
                       to="${escapeXmlAttribute(resOut)}/layout/${fragment_layout}.xml" />

     <!-- Acitivty -->
     <#if needsActivity>
     <instantiate from="root/src/app_package/MvpActivity.kt.ftl"
                            to="${escapeXmlAttribute(srcOut)}/${className}Activity.kt" />

     <instantiate from="root/src/app_package/MvpActivityLayout.xml.ftl"
                            to="${escapeXmlAttribute(resOut)}/layout/${activity_layout}.xml" />

     </#if>

    <!-- Open files -->
    <open file="${escapeXmlAttribute(srcOut)}/${className}Fragment.kt"/>
    <open file="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentModel.kt"/>
    <open file="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentView.kt"/>
    <open file="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentInteractor.kt"/>
    <open file="${escapeXmlAttribute(srcOut)}/impl/${className}FragmentPresenter.kt"/>
    <open file="${escapeXmlAttribute(resOut)}/layout/${fragment_layout}.xml"/>
    <#if needsActivity>
    <open file="${escapeXmlAttribute(srcOut)}/${className}Activity.kt"/>
    <open file="${escapeXmlAttribute(resOut)}/layout/${activity_layout}.xml"/>
    </#if>
</recipe>
