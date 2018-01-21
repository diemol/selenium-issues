<?php

namespace Tests\Feature;

use Tests\TestCase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Foundation\Testing\RefreshDatabase;
use RemoteWebDriver;

class UserTest extends TestCase
{

    /**
     * @var \RemoteWebDriver
     */

    protected $webDriver;

	public function setUp()
    {
        $capabilities = array(\WebDriverCapabilityType::BROWSER_NAME => 'firefox');
        $this->webDriver = RemoteWebDriver::create('http://hub:4444/wd/hub', $capabilities, 50000, 50000);
    }

    protected $url = 'https://whoer.net';

    public function testGitHubHome()
    {
        $this->webDriver->get($this->url);
        // checking that page title contains word 'GitHub'
        $this->assertContains('IP', $this->webDriver->getTitle());
    }

}