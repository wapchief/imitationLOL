package com.wapchief.imitationlol

/**
 * Created by apple on 2017/6/23.
 */
class MainBean {
    var img: String? = null
    var title: String? = null
    var content: String? = null
    var time: String? = null
    override fun toString(): String {
        return "MainBean{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}'
    }
}