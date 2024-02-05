describe('Simplistic Editor App - Shadow DOM', () => {
    it('Going into the Shadow DOM', async () => {
        await browser.url(`https://flutter.github.io/samples/web/simplistic_editor/`)
        await $('flt-glass-pane').waitForExist({timeout: 60000, interval: 5000})
        const semantics = await $('flt-glass-pane').shadow$('flt-semantics-placeholder')
        await browser.execute('arguments[0].click({force: true})', semantics)
        await $('flt-glass-pane').shadow$('[data-semantics-role="text-field"]').click()
        await browser.pause(60000)
        await $('flt-glass-pane').shadow$('.flt-text-editing').waitForExist()
        await $('flt-glass-pane').shadow$('.flt-text-editing').setValue('Test with WebdriverIO and Flutter')
    })
})

