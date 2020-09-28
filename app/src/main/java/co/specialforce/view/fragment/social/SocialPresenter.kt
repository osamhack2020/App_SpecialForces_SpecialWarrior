package co.specialforce.view.fragment.social

class SocialPresenter(private val view: SocialContract.View) : SocialContract.Presenter {
    override fun start() {
        view.presenter = this
    }
}