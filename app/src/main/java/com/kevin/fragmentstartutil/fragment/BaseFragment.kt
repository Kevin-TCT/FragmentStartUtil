package com.kevin.fragmentstartutil.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.animation.Animation

import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragmentDelegate
import me.yokeyword.fragmentation.anim.FragmentAnimator


/**
 * Create by KevinTu on 2018/10/12
 */
open class BaseFragment : Fragment(), ISupportFragment {
    private val mDelegate = SupportFragmentDelegate(this)
    private lateinit var _mActivity: FragmentActivity

    override fun getSupportDelegate(): SupportFragmentDelegate {
        return mDelegate
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mDelegate.onAttach(activity)
        _mActivity = mDelegate.activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDelegate.onCreate(savedInstanceState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return mDelegate.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mDelegate.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mDelegate.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        mDelegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        mDelegate.onPause()
    }

    override fun onDestroyView() {
        mDelegate.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        mDelegate.onHiddenChanged(hidden)
        super.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mDelegate.setUserVisibleHint(isVisibleToUser)
    }

    /**
     * If you want to call the start()/pop()/showHideFragment() on the onCreateXX/onActivityCreated,
     * call this method to deliver the transaction to the queue.
     * <p>
     * 在onCreate/onCreateView/onActivityCreated中使用 start()/pop()/showHideFragment(),请使用该方法把你的任务入队
     *
     * @param runnable start() , pop() or showHideFragment()
     */
    override fun enqueueAction(runnable: Runnable?) {
        mDelegate.enqueueAction(runnable)
    }

    override fun post(runnable: Runnable?) {
        mDelegate.post(runnable)
    }

    /**
     * Called when the enter-animation end.
     * 入栈动画 结束时,回调
     */
    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        mDelegate.onEnterAnimationEnd(savedInstanceState)
    }


    /**
     * Lazy initial，Called when fragment is first called.
     * <p>
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    override fun onLazyInitView(savedInstanceState: Bundle?) {
        mDelegate.onLazyInitView(savedInstanceState)
    }

    /**
     * Called when the fragment is visible.
     * 当Fragment对用户可见时回调
     * <p>
     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
     */
    override fun onSupportVisible() {
        mDelegate.onSupportVisible()
    }

    /**
     * Called when the fragment is invivible.
     * <p>
     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
     */
    override fun onSupportInvisible() {
        mDelegate.onSupportInvisible()
    }

    /**
     * Return true if the fragment has been supportVisible.
     */
    override fun isSupportVisible(): Boolean {
        return mDelegate.isSupportVisible
    }

    /**
     * Set fragment animation with a higher priority than the ISupportActivity
     * 设定当前Fragmemt动画,优先级比在SupportActivity里高
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate.onCreateFragmentAnimator()
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate.fragmentAnimator
    }

    /**
     * 设置Fragment内的全局动画
     */
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator?) {
        mDelegate.fragmentAnimator = fragmentAnimator
    }

    /**
     * 按返回键触发,前提是SupportActivity的onBackPressed()方法能被调用
     *
     * @return false则继续向上传递, true则消费掉该事件
     */
    override fun onBackPressedSupport(): Boolean {
        return mDelegate.onBackPressedSupport()
    }

    /**
     * 类似 {@link Activity#setResult(int, Intent)}
     * <p>
     * Similar to {@link Activity#setResult(int, Intent)}
     *
     * @see #startForResult(SupportFragment, int)
     */
    override fun setFragmentResult(resultCode: Int, bundle: Bundle?) {
        mDelegate.setFragmentResult(resultCode, bundle)
    }

    /**
     * 类似  {@link Activity#onActivityResult(int, int, Intent)}
     * <p>
     * Similar to {@link Activity#onActivityResult(int, int, Intent)}
     *
     * @see #startForResult(SupportFragment, int)
     */
    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        mDelegate.onFragmentResult(requestCode, resultCode, data)
    }

    /**
     * 在start(TargetFragment,LaunchMode)时,启动模式为SingleTask/SingleTop, 回调TargetFragment的该方法
     * 类似 {@link Activity#onNewIntent(Intent)}
     * <p>
     * Similar to {@link Activity#onNewIntent(Intent)}
     *
     * @param args putNewBundle(Bundle newBundle)
     * @see #start(ISupportFragment, int)
     */
    override fun onNewBundle(args: Bundle?) {
        mDelegate.onNewBundle(args)
    }

    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     *
     * @see #start(ISupportFragment, int)
     */
    override fun putNewBundle(newBundle: Bundle?) {
        mDelegate.putNewBundle(newBundle)
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/
    // 自定制Support时，可移除不必要的方法

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput() {
        mDelegate.hideSoftInput()
    }

    /**
     * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
     */
    fun showSoftInput(view: View) {
        mDelegate.showSoftInput(view)
    }

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        mDelegate.loadRootFragment(containerId, toFragment)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment, addToBackStack: Boolean, allowAnim: Boolean) {
        mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim)
    }

    /**
     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
     */
    fun loadMultipleRootFragment(containerId: Int, showPosition: Int, vararg toFragments: ISupportFragment) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, *toFragments)
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    fun showHideFragment(showFragment: ISupportFragment) {
        mDelegate.showHideFragment(showFragment)
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    fun showHideFragment(showFragment: ISupportFragment, hideFragment: ISupportFragment) {
        mDelegate.showHideFragment(showFragment, hideFragment)
    }

    fun start(toFragment: ISupportFragment) {
        mDelegate.start(toFragment)
    }

    /**
     * @param launchMode Similar to Activity's LaunchMode.
     */
    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        mDelegate.start(toFragment, launchMode)
    }

    /**
     * Launch an fragment for which you would like a result when it poped.
     */
    fun startForResult(toFragment: ISupportFragment, requestCode: Int) {
        mDelegate.startForResult(toFragment, requestCode)
    }

    /**
     * Launch a fragment while poping self.
     */
    fun startWithPop(toFragment: ISupportFragment) {
        mDelegate.startWithPop(toFragment)
    }

    fun replaceFragment(toFragment: ISupportFragment, addToBackStack: Boolean) {
        mDelegate.replaceFragment(toFragment, addToBackStack)
    }

    fun pop() {
        mDelegate.pop()
    }

    /**
     * Pop the child fragment.
     */
    fun popChild() {
        mDelegate.popChild()
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     * <p>
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    fun popTo(targetFragmentClass: Class<Unit>, includeTargetFragment: Boolean) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment)
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    fun popTo(targetFragmentClass: Class<Unit>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
    }

    fun popTo(targetFragmentClass: Class<Unit>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable, popAnim: Int) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim)
    }

    fun popToChild(targetFragmentClass: Class<Unit>, includeTargetFragment: Boolean) {
        mDelegate.popToChild(targetFragmentClass, includeTargetFragment)
    }

    fun popToChild(targetFragmentClass: Class<Unit>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable) {
        mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
    }

    fun popToChild(targetFragmentClass: Class<Unit>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable, popAnim: Int) {
        mDelegate.popToChild(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim)
    }



    fun startDontHideSelf(parentFragment: BaseFragment, toFragment: BaseFragment, launchMode: Int) {
        try {
            var transactionObj = (activity as ISupportActivity).supportDelegate.transactionDelegate
            var method = transactionObj.javaClass.getDeclaredMethod("dispatchStartTransaction", android.support.v4.app.FragmentManager::class.java, ISupportFragment::class.java, ISupportFragment::class.java, Int::class.java, Int::class.java, Int::class.java)
            method.isAccessible = true
            method.invoke(transactionObj, parentFragment.fragmentManager, parentFragment, toFragment, 0, launchMode, 2)
        } catch (e: Exception) {
            parentFragment.extraTransaction().startDontHideSelf(toFragment)
        }
    }
}
