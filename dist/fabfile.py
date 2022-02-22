#!/bin/python3
# encoding=utf-8

import subprocess as subp

from pathlib import Path

import fabric

from dotenv import load_dotenv
from invoke.context import Context
from pydantic import BaseSettings


# Load secrets
if not load_dotenv(Path(__file__).parent / "python.env"):
    raise RuntimeError("Could not load environment!")


class Settings(BaseSettings):
    remote_host: str
    remote_user: str
    remote_pass: str


def local(cmd: list[str], **kwargs):
    """Execute command as current context."""
    proc     = subp.Popen(cmd, stdout=subp.PIPE, stderr=subp.PIPE, **kwargs)
    out, err = proc.communicate()
    return out, err


def gradle(cmd: str, **kwargs):
    """Execute gradle command."""
    out, err = local(["./gradlew", cmd], cwd="..")
    if err:
        raise RuntimeError(str(err, encoding="utf-8"))
    print(str(out, encoding="utf-8"))


@fabric.task
def build(ctx):
    """Build the distributable of this project."""
    gradle("build")


@fabric.task
def deploy(ctx):
    """Deploy recent build to remote."""
    build(ctx)
