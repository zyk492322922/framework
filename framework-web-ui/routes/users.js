const router = require('koa-router')()

 router.prefix('/users')

router.get('/', async (ctx, next) => {
    console.log(ctx.sub)
    console.log(11111111111111111111)
    // try{
    //     await ctx.render('index', {
    //         title: '首页',
    //         content: ctx.sub
    //     })
    // }catch(e){
    //     ctx.render("error",{
    //         message: e,
    //         status: "500",
    //         error:e
    //     })
    // }
})

router.get('/register', async (ctx, next) => {
  await ctx.render('register', {
    title: '注册'
  })
})

router.get('/login', async (ctx, next) => {
  await ctx.render('login', {
    title: '登录'
  })
})

router.get('/forget-password' ,async (ctx, next) => {
  await ctx.render('forgetPassword', {
    title: '忘记密码'
  })
})

router.get('/change-mobile' ,async (ctx, next) => {
  await ctx.render('changeMobile', {
    title: '修改密码'
  })
})

router.get('/change-mobile/success' ,async (ctx, next) => {
  await ctx.render('result', {
    title: '手机号修改成功'
  })
})

router.get('/change-mobile/faild' ,async (ctx, next) => {
  await ctx.render('result', {
    title: '手机号修改失败'
  })
})

router.get('/change-password' ,async (ctx, next) => {
  await ctx.render('result', {
    title: '修改登录密码'
  })
})

router.get('/change-password/success' ,async (ctx, next) => {
  await ctx.render('result', {
    title: '登录密码修改成功'
  })
})

router.get('/change-password/faild' ,async (ctx, next) => {
  await ctx.render('result', {
    title: '登录密码修改失败'
  })
})


module.exports = router
