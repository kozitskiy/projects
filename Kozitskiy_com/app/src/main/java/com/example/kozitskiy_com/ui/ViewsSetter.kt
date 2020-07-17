package com.example.kozitskiy_com.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.example.kozitskiy_com.*
import com.example.kozitskiy_com.network.models.DataApiObjects
import com.example.kozitskiy_com.utils.*
import kotlinx.android.synthetic.main.item_about_me_tag.view.*
import kotlinx.android.synthetic.main.item_portfolio.view.*
import kotlinx.android.synthetic.main.item_profession.view.*
import kotlinx.android.synthetic.main.item_skills.view.*
import kotlinx.android.synthetic.main.item_technology.view.*
import kotlinx.android.synthetic.main.item_what_i_do.view.*
import kotlinx.android.synthetic.main.template_about_me.*
import kotlinx.android.synthetic.main.template_contacts.*
import kotlinx.android.synthetic.main.template_footer.*
import kotlinx.android.synthetic.main.template_header.*
import kotlinx.android.synthetic.main.template_main_part.*
import kotlinx.android.synthetic.main.template_main_part.email_address
import kotlinx.android.synthetic.main.template_main_part.location_address
import kotlinx.android.synthetic.main.template_main_part.phone_number
import kotlinx.android.synthetic.main.template_portfolio.*
import kotlinx.android.synthetic.main.template_skills.*
import kotlinx.android.synthetic.main.template_technologies.*
import kotlinx.android.synthetic.main.template_what_i_do.*

class ViewsSetter(
    private val dataApiObjects: DataApiObjects,
    private val indexPageActivity: IndexPageActivity
) {

    fun setAllViews() {
        setAppTitle()
        setHeaderBlock()
        setMainDataBlock()
        setAboutMeBlock()
        setWhatIDoBlock()
        setTechnologiesBlock()
        setSkillsBlock()
        setPortfolioBlock()
        setContactsBlock()
        setFooterBlock()
    }

    private fun setAppTitle() {
        indexPageActivity.title = dataApiObjects.siteName;
    }

    @SuppressLint("ResourceType")
    private fun setHeaderBlock() {

        val popupMenu = PopupMenu(indexPageActivity, indexPageActivity.img_menu_button)
        popupMenu.inflate(R.layout.template_main_menu)

        popupMenu.menu.apply {
            getItem(0).title = dataApiObjects.menuItems?.home
            getItem(1).title = dataApiObjects.menuItems?.about
            getItem(2).title = dataApiObjects.menuItems?.skills
//            getItem(3).title = dataApiObjects.menuItems?.education
//            getItem(4).title = dataApiObjects.menuItems?.experience
            getItem(3).title = dataApiObjects.menuItems?.portfolio
            getItem(4).title = dataApiObjects.menuItems?.contacts
        }

        ViewListeners.setHeaderClickListeners(indexPageActivity, popupMenu)
    }

    private fun setMainDataBlock() {
        dataApiObjects.homeInformation?.developerPhotoLink?.let {
            setImageWithGlide(
                indexPageActivity.img_owner,
                it,
                JPG_FORMAT_IMG_TO_LOAD
            )
        }
        indexPageActivity.apply {
            head_name.text = dataApiObjects.headName
            txt_im_tag.text = dataApiObjects.homeInformation?.helloTag
            txt_owner_name.text = dataApiObjects.ownerName
            setCallItem(phone_number)
            setEmailItem(email_address)
            location_address.text = dataApiObjects.contacts?.location
        }
        setProfessionItems()
        setSocialItems(indexPageActivity.header_social_block)
    }

    private fun setEmailItem(textView: TextView) {
        textView.text = dataApiObjects.contacts?.emailToView
        dataApiObjects.contacts?.emailToSend?.let {
            ViewListeners.setStartActionActivityClickListener(
                indexPageActivity,
                textView,
                it,
                Intent.ACTION_SENDTO
            )
        }
    }

    private fun setCallItem(textView: TextView) {
        textView.text = dataApiObjects.contacts?.telephoneToView
        dataApiObjects.contacts?.telephoneToCall?.let {
            ViewListeners.setStartActionActivityClickListener(
                indexPageActivity,
                textView,
                it,
                Intent.ACTION_CALL
            )
        }
    }

    private fun setSocialItems(view: View) {
        val socialItems = dataApiObjects.social
        view.apply {
            val layout = this as ViewGroup
            visibility = View.VISIBLE
            if (socialItems != null) {
                for (socialItem in socialItems) {
                    val socialView = getItemView(indexPageActivity, layout, R.layout.item_social)

                    setImageWithGlide(socialView as ImageView, socialItem.socialIcon, PNG_FORMAT_IMG_TO_LOAD)
                    ViewListeners.setStartActionActivityClickListener(
                        indexPageActivity,
                        socialView,
                        socialItem.socialLink,
                        Intent.ACTION_VIEW
                    )
                    addView(socialView)
                }
            }
        }
    }

    private fun setProfessionItems() {
        val developerItems = dataApiObjects.homeInformation?.developerSubBlock
        indexPageActivity.block_professions.apply {
            val layout = this as ViewGroup
            if (developerItems != null) {
                visibility = View.VISIBLE
                for (developerStr in developerItems) {
                    val professionView =
                        getItemView(indexPageActivity, layout, R.layout.item_profession)
                    professionView.professional_item.text = developerStr
                    addView(professionView)
                }
            }
        }
    }

    private fun setAboutMeBlock() {
        dataApiObjects.aboutMeBlock?.imageManLink?.let {
            setImageWithGlide(
                indexPageActivity.image_man_about,
                it, SVG_FORMAT_IMG_TO_LOAD
            )
        }

        indexPageActivity.apply {
            about_me_title.text = dataApiObjects.aboutMeBlock?.title
            about_me_text.text = dataApiObjects.aboutMeBlock?.text?.replaceBrToNewLine()
            download_btn.text = dataApiObjects.aboutMeBlock?.buttonDownloadCv?.title
            dataApiObjects.aboutMeBlock?.buttonDownloadCv?.cvLink?.let {
                ViewListeners.setStartActionActivityClickListener(
                    indexPageActivity,
                    download_btn,
                    it,
                    Intent.ACTION_VIEW
                )
            }
        }
        setAboutMeTagItems()
    }

    private fun setAboutMeTagItems() {
        val aboutMeTags = dataApiObjects.aboutMeBlock?.tags
        indexPageActivity.about_me_tags.apply {
            val layout = this as ViewGroup
            if (aboutMeTags != null) {
                visibility = View.VISIBLE
                for (aboutMeTag in aboutMeTags) {
                    val aboutMeTagView =
                        getItemView(indexPageActivity, layout, R.layout.item_about_me_tag)
                    aboutMeTagView.about_me_item.text = aboutMeTag
                    addView(aboutMeTagView)
                }
            }
        }
    }

    private fun setWhatIDoBlock() {
        indexPageActivity.what_i_do_title.text = dataApiObjects.whatIDoBlocks?.blockTitle
        val whatIDoItems = dataApiObjects.whatIDoBlocks?.blocks
        indexPageActivity.what_i_do_items.apply {
            val layout = this as ViewGroup
            if (whatIDoItems != null) {
                visibility = View.VISIBLE
                for (whatIDoItem in whatIDoItems) {
                    val whatIDoView =
                        getItemView(indexPageActivity, layout, R.layout.item_what_i_do)
                    whatIDoView.what_i_do_name.text = whatIDoItem.title
                    setImageWithGlide( whatIDoView.what_i_do_image, whatIDoItem.image, PNG_FORMAT_IMG_TO_LOAD)
                    whatIDoView.what_i_do_text.text = whatIDoItem.text
                    addView(whatIDoView)
                }
            }
        }
    }

    private fun setTechnologiesBlock() {
        indexPageActivity.technologies_block_title.text = dataApiObjects.technologiesBlock?.title
        val technologyItems = dataApiObjects.technologiesBlock?.technologies
        indexPageActivity.technologies_wrap.apply {
            val layout = this as ViewGroup
            if (technologyItems != null) {
                visibility = View.VISIBLE
                for (technologyItem in technologyItems) {
                    val technologyView =
                        getItemView(indexPageActivity, layout, R.layout.item_technology)
                    technologyView.elem_technology.text = technologyItem
                    addView(technologyView)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSkillsBlock() {
        indexPageActivity.skills_title.text = dataApiObjects.technicalSkillsBlock?.title
        val skillsItems = dataApiObjects.technicalSkillsBlock?.skills
        indexPageActivity.skills_items.apply {
            val layout = this as ViewGroup
            if (skillsItems != null) {
                visibility = View.VISIBLE
                for (skillsItem in skillsItems) {
                    val skillsView = getItemView(indexPageActivity, layout, R.layout.item_skills)
                    skillsView.skills_name.text = skillsItem.name
                    skillsView.skills_percent.text = "${skillsItem.level}%"
                    skillsView.skills_progress.progress = skillsItem.level
                    addView(skillsView)
                }
            }
        }
    }

    private fun setPortfolioBlock() {
        indexPageActivity.portfolio_block_title.text = dataApiObjects.portfolioBlocks?.title
        val portfolioItems = dataApiObjects.portfolioBlocks?.blocks
        indexPageActivity.portfolio_items.apply {
            val layout = this as ViewGroup
            if (portfolioItems != null) {
                visibility = View.VISIBLE
                for (portfolioItem in portfolioItems) {
                    val portfolioView =
                        getItemView(indexPageActivity, layout, R.layout.item_portfolio)

                    portfolioView.portfolio_item_title.text = portfolioItem.title
                    portfolioView.portfolio_item.tag = portfolioItem.type
                    setImageWithGlide(
                        portfolioView.portfolio_image,
                        portfolioItem.imgLink,
                        PNG_FORMAT_IMG_TO_LOAD
                    )

                    ViewListeners.setStartActionActivityClickListener(
                        indexPageActivity,
                        portfolioView.portfolio_item,
                        portfolioItem.linkToGo,
                        Intent.ACTION_VIEW
                    )

                    ViewListeners.setPortfolioCategoryClickListener(indexPageActivity)
                    addView(portfolioView)
                }
            }
        }
    }

    private fun setContactsBlock() {
        indexPageActivity.apply {
            setCallItem(contacts_phone_number)
            setEmailItem(contacts_email_address)
            contacts_location_address.text = dataApiObjects.contacts?.location
        }
        ViewListeners.setSendEmailClickListener(indexPageActivity)
    }

    @SuppressLint("SetTextI18n")
    private fun setFooterBlock(){
        indexPageActivity.footer_owner_name.text = dataApiObjects.ownerName
        indexPageActivity.footer_site_year.text = "© ${dataApiObjects.siteCurrentYear}"
        setSocialItems(indexPageActivity.footer_social_block)
    }
}