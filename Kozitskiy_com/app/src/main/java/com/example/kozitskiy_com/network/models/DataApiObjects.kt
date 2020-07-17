package com.example.kozitskiy_com.network.models

import com.google.gson.annotations.SerializedName

data class DataApiObjects(
    @SerializedName("site_name")
    val siteName: String? = null,
    @SerializedName("page_404_name")
    val page404Name: String? = null,
    @SerializedName("head_name")
    val headName: String? = null,
    @SerializedName("owner_name")
    val ownerName: String? = null,
    @SerializedName("site_current_year")
    val siteCurrentYear: String? = null,
    val contacts: Contacts? = null,
    val social: List<Social>? = null,
    @SerializedName("menu_items")
    val menuItems: MenuItems? = null,
    @SerializedName("home_information")
    val homeInformation: HomeInformation? = null,
    @SerializedName("about_me_block")
    val aboutMeBlock: AboutMeBlock? = null,
    @SerializedName("what_i_do_blocks")
    val whatIDoBlocks: WhatIDoBlocks? = null,
    @SerializedName("work_experience_blocks")
    val workExperienceBlocks: WorkExperienceBlocks? = null,
    @SerializedName("technical_skills_block")
    val technicalSkillsBlock: TechnicalSkillsBlock? = null,
    @SerializedName("technologies_block")
    val technologiesBlock: TechnologiesBlock? = null,
    @SerializedName("education_block")
    val educationBlock: EducationBlocks? = null,
    @SerializedName("additional_education_block")
    val additionalEducationBlock: AdditionalEducationBlocks? = null,
    @SerializedName("portfolio_blocks")
    val portfolioBlocks: PortfolioBlocks? = null
)

data class HomeInformation(
    @SerializedName("developer_photo_link")
    val developerPhotoLink: String,
    @SerializedName("hello_tag")
    val helloTag: String,
    @SerializedName("developer_sub_block")
    val developerSubBlock: List<String>
)

data class MenuItems(
    val home: String,
    val about: String,
    val skills: String,
    val education: String,
    val experience: String,
    val portfolio: String,
    val contacts: String
)

data class Social(
    @SerializedName("social_icon")
    val socialIcon: String,
    @SerializedName("social_link")
    val socialLink: String
)

data class PortfolioBlocks(
    val title: String,
    val blocks: List<PortfolioBlock>
)

data class PortfolioBlock(
    val title: String,
    val desc: String,
    @SerializedName("img_link")
    val imgLink: String,
    @SerializedName("link_to_go")
    val linkToGo: String,
    val type: String
)

data class AboutMeBlock(
    val title: String,
    val text: String,
    val tags: List<String>,
    @SerializedName("image_man_link")
    val imageManLink: String,
    @SerializedName("button_download_cv")
    val buttonDownloadCv: ButtonDownloadCv
)

data class ButtonDownloadCv(
    val title: String,
    @SerializedName("cv_link")
    val cvLink: String
)

data class AdditionalEducationBlocks(
    val title: String,
    val blocks: List<AdditionalEducationBlock>
)

data class AdditionalEducationBlock(
    val title: String,
    val location: String,
    @SerializedName("period_time")
    val periodTime: String,
    @SerializedName("period_month")
    val periodMonth: String,
    @SerializedName("knowledge_1")
    val knowledge1: String,
    @SerializedName("knowledge_2")
    val knowledge2: String
)

data class Contacts(
    @SerializedName("telephone_to_call")
    val telephoneToCall: String,
    @SerializedName("telephone_to_view")
    val telephoneToView: String,
    @SerializedName("email_to_view")
    val emailToView: String,
    @SerializedName("email_to_send")
    val emailToSend: String,
    val location: String
)

data class EducationBlocks(
    val title: String,
    val blocks: List<EducationBlock>
)

data class EducationBlock(
    val title: String,
    val faculty: String,
    @SerializedName("period_time")
    val periodTime: String,
    @SerializedName("period_month")
    val periodMonth: String
)

data class WhatIDoBlocks(
    @SerializedName("block_title")
    val blockTitle: String,
    val blocks: List<WhatIDoBlock>
)

data class WhatIDoBlock(
    val title: String,
    val image: String,
    val text: String
)

data class WorkExperienceBlocks(
    @SerializedName("block_title")
    val blockTitle: String,
    val blocks: List<WorkExperienceBlock>
)

data class WorkExperienceBlock(
    val title: String,
    @SerializedName("period_time")
    val periodTime: String,
    @SerializedName("period_months")
    val periodMonths: String,
    val texts: List<String>
)

data class TechnicalSkillsBlock(
    val title: String,
    val skills: List<Skill>
)

data class Skill(
    val name: String,
    val level: Int
)

data class TechnologiesBlock(
    val title: String,
    val technologies: List<String>
)